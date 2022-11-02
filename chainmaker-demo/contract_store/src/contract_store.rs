/// 
/// Copyright (C) BABEC. All rights reserved.
/// 
/// SPDX-License-Identifier: Apache-2.0
/// 

use crate::easycodec::*;
use crate::sim_context;
use sim_context::*;

// 安装合约时会执行此方法，必须
#[no_mangle]
pub extern "C" fn init_contract() {
    // 安装时的业务逻辑，内容可为空
    sim_context::log("init_contract");
}

// 升级合约时会执行此方法，必须
#[no_mangle]
pub extern "C" fn upgrade() {
    // 升级时的业务逻辑，内容可为空
    sim_context::log("upgrade");
    let ctx = &mut sim_context::get_sim_context();
    ctx.ok("upgrade success".as_bytes());
}

struct DataBlock {
    data_hash: String,
    data_name: String,
    time: i32,
    ec: EasyCodec,
}

impl DataBlock {
    fn new_data(data_hash: String, data_name: String, time: i32) -> DataBlock {
        let mut ec = EasyCodec::new();
        ec.add_string("data_hash", data_hash.as_str());
        ec.add_string("data_name", data_name.as_str());
        ec.add_i32("time", time);
        DataBlock {
            data_hash,
            data_name,
            time,
            ec,
        }
    }

    fn get_emit_event_data(&self) -> Vec<String> {
        let mut arr: Vec<String> = Vec::new();
        arr.push(self.data_hash.clone());
        arr.push(self.data_name.clone());
        arr.push(self.time.to_string());
        arr
    }

    fn to_json(&self) -> String {
        self.ec.to_json()
    }

    fn marshal(&self) -> Vec<u8> {
        self.ec.marshal()
    }

    fn unmarshal(data: &Vec<u8>) -> DataBlock {
        let ec = EasyCodec::new_with_bytes(data);
        DataBlock {
            data_hash: ec.get_string("data_hash").unwrap(),
            data_name: ec.get_string("data_name").unwrap(),
            time: ec.get_i32("time").unwrap(),
            ec,
        }
    }
}

#[no_mangle]
pub extern "C" fn save() {
    // 获取上下文
    let ctx = &mut sim_context::get_sim_context();

    // 获取传入参数
    let data_hash = ctx.arg_as_utf8_str("data_hash");
    let data_name = ctx.arg_as_utf8_str("data_name");
    let time_str = ctx.arg_as_utf8_str("time");

    // 校验参数
    if data_hash.len() == 0 {
        let msg = "data_hash is null";
        ctx.log(&msg);
        ctx.error(&msg);
        return;
    }

    let r_i32 = time_str.parse::<i32>();
    if r_i32.is_err() {
        let msg = format!("time is {:?} not int32 number.", time_str);
        ctx.log(&msg);
        ctx.error(&msg);
        return;
    }
    // 构造结构体
    let time: i32 = r_i32.unwrap();
    let fact = DataBlock::new_data(data_hash, data_name, time);

    // 事件
    ctx.emit_event("topic_vx", &fact.get_emit_event_data());

    // 序列化后存储
    ctx.put_state(
        "datablock_ec",
        fact.data_hash.as_str(),
        fact.marshal().as_slice(),
    );
}

// find_by_data_hash 根据data_hash查询存证数据
#[no_mangle]
pub extern "C" fn find_by_data_hash() {
    // 获取上下文
    let ctx = &mut sim_context::get_sim_context();

    // 获取传入参数
    let data_hash = ctx.arg_as_utf8_str("data_hash");

    // 校验参数
    if data_hash.len() == 0 {
        ctx.log("data_hash is null");
        ctx.ok("".as_bytes());
        return;
    }

    // 查询
    let r = ctx.get_state("datablock_ec", &data_hash);

    // 校验返回结果
    if r.is_err() {
        ctx.log("get_state fail");
        ctx.error("get_state fail");
        return;
    }
    let fact_vec = r.unwrap();
    if fact_vec.len() == 0 {
        ctx.log("None");
        ctx.ok("".as_bytes());
        return;
    }

    // 查询
    let r = ctx.get_state("datablock_ec", &data_hash).unwrap();
    let fact = DataBlock::unmarshal(&r);
    let json_str = fact.to_json();

    // 返回查询结果
    ctx.ok(json_str.as_bytes());
    ctx.log(&json_str);
}