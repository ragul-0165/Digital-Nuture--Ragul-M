# Decompiling Java Class Files

This guide explains how to decompile Java `.class` files using different tools.

## Using JD-GUI

1. **Download JD-GUI** from [GitHub Releases](https://github.com/java-decompiler/jd-gui/releases)
2. **Install** the appropriate version for your OS
3. **Run JD-GUI**
4. **Open** your `.class` file using File > Open File...
5. **View** the decompiled source code

## Using CFR (Class File Reader)

1. **Download CFR** from [www.benf.org/other/cfr/](http://www.benf.org/other/cfr/)
2. **Run CFR** from the command line:
   ```
   java -jar cfr-x.x.x.jar YourClassFile.class
   ```
   (Replace x.x.x with the CFR version and YourClassFile with your class file name)

## Example Workflow

1. First, compile a Java file:
   ```
   javac HelloWorld.java
   ```
   This will generate `HelloWorld.class`

2. Then, decompile it with CFR:
   ```
   java -jar cfr-x.x.x.jar HelloWorld.class
   ```

## Notes

- Decompilation works best on standard Java code without obfuscation
- Some decompilers might not perfectly reconstruct the original source
- Always respect software licenses when decompiling code

## Common Issues

- If you get "Class file version X is not supported" error, try a newer version of the decompiler
- For obfuscated code, try multiple decompilers as they may handle obfuscation differently
