### 说明
1. 数据库注释：DBCommentGenerator
    1.1、配置pom的plugin
				<plugin>
					<groupId>org.mybatis.generator</groupId>
					<artifactId>mybatis-generator-maven-plugin</artifactId>
                    <configuration>
                        <!--允许移动生成的文件-->
                        <verbose>true</verbose>
                        <!--允许覆盖生成的文件-->
                        <overwrite>true</overwrite>
                    </configuration>
					<version>1.3.5</version>
					<executions>
						<execution>
							<id>Generate MyBatis Artifacts</id>
							<goals>
								<goal>generate</goal>
							</goals>
						</execution>
					</executions>
					<dependencies>
						<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
						<!-- 配置这个依赖主要是为了等下在配置MG的时候可以不用配置classPathEntry这样的一个属性 -->
						<!-- 避免代码的耦合度太高 -->
						<dependency>
							<groupId>mysql</groupId>
							<artifactId>mysql-connector-java</artifactId>
							<version>5.1.38</version>
						</dependency>

                        <dependency>
                            <groupId>com.lhx.common</groupId>
                            <artifactId>mybatis-plugin-ext</artifactId>
                            <version>1.0-SNAPSHOT</version>
                        </dependency>
					</dependencies>
				</plugin>
    1.2、在generatorConfig.xml中修改
            <!-- 这里的type里写的是你的实现类的类全路径 -->
            <commentGenerator type="com.lhx.common.mybatis.plugin.DBCommentGenerator">
                <property name="suppressDate" value="false"/> <!-- 是否去除注释时间戳 false时打开时间标志，true时关闭-->
                <property name="suppressAllComments" value="false"/>   <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
                <property name="javaFileEncoding" value="UTF-8"/>
            </commentGenerator>      
2、自定分页：MutiDatasourcePaginationPlugin
    2.1、除了1.1之外还需将依赖添加POM
        <dependency>
            <groupId>com.lhx.common</groupId>
            <artifactId>mybatis-plugin-ext</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    2.2、增加插件配置
        <plugin type="com.lhx.common.mybatis.plugin.MutiDatasourcePaginationPlugin"></plugin>
    2.3、分页是以Example方式，不要设置为false
        <table tableName="users" domainObjectName="User"/> 