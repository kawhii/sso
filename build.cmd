@echo off


@rem Check for mvn in path, use it if found, otherwise use maven wrapper
@set MAVEN_CMD=mvn
@where /q mvn


@if "%1" == "" call:help
@if "%1" == "sso-server" call:sso-server
@if "%1" == "sso-management" call:sso-management
@if "%1" == "sso-config" call:sso-config %2 %3 %4
@if "%1" == "cas-client-demo" call:cas-client-demo %2 %3 %4
@if "%1" == "shiro-client-demo" call:shiro-client-demo %2 %3 %4
@if "%1" == "help" call:help
@if "%1" == "run-all" call:run-all

@rem function section starts here
@goto:eof

:help
    @echo "Usage: build.bat [help|sso-server|sso-management|sso-config|cas-client-demo|shiro-client-demo|run-all]"

    @echo "sso-server: CAS server to run(2)"
    @echo "sso-management: Cas Management"
    @echo "sso-config: Config Server(1)"
    @echo "cas-client-demo: CasClient Demo"
    @echo "shiro-client-demo: ShiroDemo"
    @echo "run-all: Run all server"
@goto:eof

:sso-server
	@echo Startting sso server...
    call sso-server/build.cmd run
@goto:eof

:sso-management
	@echo Startting cas management...
    call sso-management /build.cmd run
@goto:eof

:sso-config
	@echo Startting config Server...
    call %MAVEN_CMD% spring-boot:run -T 5 %1 %2 %3 -f sso-config/pom.xml
@goto:eof

:cas-client-demo
	@echo Startting cas client demo...
    call %MAVEN_CMD% jetty:run -f sso-client-demo/sso-cas-client-demo/pom.xml
@goto:eof

:shiro-client-demo
	@echo Startting shiro client demo...
    call %MAVEN_CMD% spring-boot:run -T 5 %1 %2 %3 -f sso-client-demo/sso-client-shiro-demo/pom.xml
@goto:eof

:run-all
	@echo TODO...
@goto:eof
