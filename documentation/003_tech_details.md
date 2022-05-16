# Environment setup

Example:

* Eclipse IDE for Java Developers (includes Incubating components)

Version: 2022-03 (4.23.0)

* JDK 17
* JavaFX 17
* Scene Builder

## Installation

## How to Run

Make sure to have Java 17 and also JavaFX 17.
The correct location for the javaFX jar files is under `C:\javafx-sdk-17.0.2\lib`
You have to add this file as the VM argument: `--module-path "C:\javafx-sdk-17.0.2\lib" --add-modules javafx.controls,javafx.fxml`

Make sure to have the right version of JRE System Library. For running the project, you need to use JRE 17. To change this library, go to Window -> Preferences -> Java -> Installed JREs and add the JRE 17. Then just set the current JRE to Project JRE in the Build Path and the program will run.
