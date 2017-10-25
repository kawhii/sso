@echo off


@rem Check for mvn in path, use it if found, otherwise use maven wrapper
@set MAVEN_CMD=mvn
@set CURR_DIR=%cd%
@where /q mvn


@if "%1" == "" call:help
@if "%1" == "sso-server" call:sso-server
@if "%1" == "sso-management" call:sso-management
@if "%1" == "sso-config" call:sso-config %2 %3 %4
@if "%1" == "cas-client-demo" call:cas-client-demo %2 %3 %4
@if "%1" == "shiro-client-demo" call:shiro-client-demo %2 %3 %4
@if "%1" == "help" call:help
@if "%1" == "run" call:run-all
@if "%1" == "init" call:init

@rem function section starts here
@goto:eof

:help
    @echo "Usage: build.bat [help|sso-server|sso-management|sso-config|cas-client-demo|shiro-client-demo|run-all|hosts]"

    @echo 1. sso-config: Config Server
    @echo 2. sso-server: CAS Server
    @echo 3. sso-management: Cas Management
    @echo 4. cas-client-demo: CasClient Demo
    @echo 5. shiro-client-demo: ShiroDemo
    @echo 6. run: Run all server
    @echo 7. init: set '127.0.0.1 passport.sso.cm' to HOSTS, import cert to %JAVA_HOME%\jre\lib\security\cacerts
@goto:eof

:sso-server
	@echo Stating sso server...
	cd %CURR_DIR%/sso-server
	start "sso-server" build.cmd run
	cd %CURR_DIR%
@goto:eof

:sso-management
	@echo Stating cas management...
	cd %CURR_DIR%/sso-management
    start "sso-management" build.cmd run
    cd %CURR_DIR%
@goto:eof

:sso-config
	@echo Stating config Server...
	cd %CURR_DIR%
	start "sso-config" %MAVEN_CMD% spring-boot:run -T 5 %1 %2 %3 -f sso-config/pom.xml
@goto:eof

:cas-client-demo
	@echo Stating cas client demo...
	cd %CURR_DIR%
	start "cas-client-demo" %MAVEN_CMD% jetty:run -f sso-client-demo/sso-cas-client-demo/pom.xml
@goto:eof

:shiro-client-demo
	@echo Stating shiro client demo...
	cd %CURR_DIR%
	start "shiro-client-demo" %MAVEN_CMD% spring-boot:run -T 5 %1 %2 %3 -f sso-client-demo/sso-client-shiro-demo/pom.xml
@goto:eof

:sso-client-proxy-demo
	@echo Stating proxy client demo...
	cd %CURR_DIR%
	start "sso-client-proxy-demo" %MAVEN_CMD% spring-boot:run -T 5 %1 %2 %3 -f sso-client-demo/sso-client-proxy-demo/pom.xml
@goto:eof


::运行所有服务
:run-all
	@echo Starting run all...
	call %MAVEN_CMD% clean
	call:sso-config
	call:sso-server
	call:cas-client-demo
	call:shiro-client-demo
	call:sso-management
	call:sso-client-proxy-demo
	@echo All server have started.
@goto:eof


::导入证书到java环境、设置host
:init
    echo init project...
    call mvn install
	@echo init ssl and hosts...
	::@echo 127.0.0.1 passport.sso.com >>C:\WINDOWS\system32\drivers\etc\hosts
	if exist "tomcat.cer" (echo file exists delete... & del tomcat.cer)
	echo Enter password: "123456"
	keytool -exportcert -alias passport.sso.com -keystore %CURR_DIR%/sso-server/src/main/resources/tomcat.keystore  -file tomcat.cer -rfc
	echo Enter password: "changeit" and next to Y
	keytool -import -alias passport.sso.com -keystore %JAVA_HOME%\jre\lib\security\cacerts -file tomcat.cer -trustcacerts
@goto:eof
