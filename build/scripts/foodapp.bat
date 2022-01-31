@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  foodapp startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Resolve any "." and ".." in APP_HOME to make it shorter.
for %%i in ("%APP_HOME%") do set APP_HOME=%%~fi

@rem Add default JVM options here. You can also use JAVA_OPTS and FOODAPP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS="--add-opens=javafx.base/com.sun.javafx.event=ALL-UNNAMED"

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto execute

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto execute

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\foodapp-0.0.0.jar;%APP_HOME%\lib\controlsfx-11.0.0-RC2.jar;%APP_HOME%\lib\javafx-fxml-11.0.2-linux.jar;%APP_HOME%\lib\javafx-fxml-11.0.2.jar;%APP_HOME%\lib\javafx-web-11.0.2-linux.jar;%APP_HOME%\lib\javafx-web-11.0.2.jar;%APP_HOME%\lib\javafx-controls-11.0.2-linux.jar;%APP_HOME%\lib\javafx-controls-11.0.2.jar;%APP_HOME%\lib\javafx-media-11.0.2-linux.jar;%APP_HOME%\lib\javafx-media-11.0.2.jar;%APP_HOME%\lib\javafx-graphics-11.0.2-linux.jar;%APP_HOME%\lib\javafx-graphics-11.0.2.jar;%APP_HOME%\lib\javafx-base-11.0.2-linux.jar;%APP_HOME%\lib\javafx-base-11.0.2.jar;%APP_HOME%\lib\ikonli-javafx-11.1.0.jar;%APP_HOME%\lib\ikonli-fontawesome5-pack-11.1.0.jar;%APP_HOME%\lib\ikonli-material-pack-11.1.0.jar;%APP_HOME%\lib\griffon-javafx-2.15.1.jar;%APP_HOME%\lib\gson-2.8.5.jar;%APP_HOME%\lib\ikonli-core-11.1.0.jar;%APP_HOME%\lib\griffon-core-2.15.1.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar


@rem Execute foodapp
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %FOODAPP_OPTS%  -classpath "%CLASSPATH%" app.foodapp.FoodApp %*

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable FOODAPP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%FOODAPP_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
