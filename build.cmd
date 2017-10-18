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
@if "%1" == "run-all" call:run-all
@if "%1" == "hosts" call:hosts
@if "%1" == "init" call:init

@rem function section starts here
@goto:eof

:help
    @echo "Usage: build.bat [help|sso-server|sso-management|sso-config|cas-client-demo|shiro-client-demo|run-all|hosts]"

    @echo "sso-server: CAS server to run(2)"
    @echo "sso-management: Cas Management"
    @echo "sso-config: Config Server(1)"
    @echo "cas-client-demo: CasClient Demo"
    @echo "shiro-client-demo: ShiroDemo"
    @echo "run-all: Run all server"
    @echo "hosts: set '127.0.0.1 passport.sso.cm' to HOSTS"
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

:run-all
	@echo Starting run all...
	call:sso-config
	call:sso-server
	call:cas-client-demo
	call:shiro-client-demo
	call:sso-management
@goto:eof

:hosts
	@echo start to set hosts...
	@echo 127.0.0.1 passport.sso.co >>C:\WINDOWS\system32\drivers\etc\hosts
	@echo success.
@goto:eof

:init
	@echo init ssl and hosts...
@goto:eof
