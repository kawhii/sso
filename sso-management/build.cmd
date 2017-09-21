@echo off

set CAS_DIR=\etc\cas
set CONFIG_DIR=\etc\cas\config
set DNAME="CN = cas.example.org OU = Example OU = Org  C = US"

@rem Check for mvn in path, use it if found, otherwise use maven wrapper
@set MAVEN_CMD=mvn
@where /q mvn
@if %ERRORLEVEL% neq 0 set MAVEN_CMD=.\mvnw.bat

@if "%1" == "" call:help
@if "%1" == "copy" call:copy
@if "%1" == "clean" call:clean %2 %3 %4
@if "%1" == "package" call:package %2 %3 %4
@if "%1" == "debug" call:debug %2 %3 %4
@if "%1" == "run" call:run %2 %3 %4
@if "%1" == "help" call:help
@if "%1" == "gencert" call:gencert

@rem function section starts here
@goto:eof

:copy
    @echo "Creating configuration directory under %CONFIG_DIR%"
    if not exist %CONFIG_DIR% mkdir %CONFIG_DIR%

    @echo "Copying configuration files from etc/cas to /etc/cas"
    xcopy /S /Y etc\cas\* \etc\cas
@goto:eof

:help
    @echo "Usage: build.bat [copy|clean|package|run|debug|gencert] [optional extra args for maven]"
    @echo "To get started on a clean system, run "build.bat copy" and "build.bat gencert", then "build.bat run"
    @echo "Note that using the copy or gencert arguments will create and/or overwrite the %CAS_DIR% which is outside this project"
@goto:eof

:clean
    call %MAVEN_CMD% clean %1 %2 %3
    exit /B %ERRORLEVEL%
@goto:eof

:package
    call %MAVEN_CMD% clean package -T 5 %1 %2 %3
    exit /B %ERRORLEVEL%
@goto:eof

:debug
    call:package %1 %2 %3 & java -Xdebug -Xrunjdwp:transport=dt_socket,address=5000,server=y,suspend=n -jar target/cas-management.war
@goto:eof

:run
    call:package %1 %2 %3 & java -jar target/cas-management.war
@goto:eof

:gencert
    where /q keytool
    if ERRORLEVEL 1 (
        @echo Java keytool.exe not found in path. 
        exit /B
    ) else (
        if not exist %CAS_DIR% mkdir %CAS_DIR%
        echo Generating self-signed SSL cert for %DNAME% in %CAS_DIR%\thekeystore
        keytool -genkeypair -alias cas -keyalg RSA -keypass changeit -storepass changeit -keystore %CAS_DIR%\thekeystore -dname %DNAME%
    )
@goto:eof
