package com.github.bjlhx15.common.test.other;

/**
 * @author lihongxu6
 * @version 1.0
 * @className FinalizeEscapeGC
 * @description TODO
 * @date 2021-03-03 10:34
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive(){
        System.out.println("yes , I am still alive:)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method execued!");
        FinalizeEscapeGC.SAVE_HOOK=this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK=new FinalizeEscapeGC();
        //对象第一次成功拯救自己
        SAVE_HOOK=null;
        System.gc();
        //因为finalize 方法优先级底，暂停0.5s等待他
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, I am dead1:(");
        }
        //合上面代码一样，自救失败
        SAVE_HOOK=null;
        System.gc();
        Thread.sleep(500);
        if(SAVE_HOOK!=null){
            SAVE_HOOK.isAlive();
        }else{
            System.out.println("no, I am dead2:(");
        }
    }
}
