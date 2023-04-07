package com.example;


import com.example.simplelang.antlr.SimpleLanguageLexer;
import com.example.simplelang.antlr.SimpleLanguageParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class SimpleLanguageCompiler {
    public static void main(String[] args) throws Exception {

        // 소스 코드를 읽어들입니다.
        InputStream fis = SimpleLanguageCompiler.class.getResourceAsStream("/my.sl");
        SimpleLanguageLexer lexer = new SimpleLanguageLexer(CharStreams.fromStream(fis));
        SimpleLanguageParser parser = new SimpleLanguageParser(new CommonTokenStream(lexer));

        // 파서를 실행하여 문법을 분석합니다.
        SimpleLanguageParser.ProgramContext programContext = parser.program();

        // 바이트코드를 생성합니다.
        BytecodeGenerator generator = new BytecodeGenerator();
        byte[] bytecode = generator.generate(programContext);

        // 생성된 바이트코드를 .class 파일로 저장합니다. 여기까지가 컴파일러의 역할입니다.
        try (FileOutputStream fos = new FileOutputStream("d:\\tmp\\MyGeneratedClass.class")) {
            fos.write(bytecode);
        }

        // 바이트코드를 실행합니다. tmp디렉토리에서  java MyGeneratedClass 로 실행하면 됩니다. 아래는 그냥 byte코드로 실행하는 코드일뿐입니다.
        CustomClassLoader classLoader = new CustomClassLoader();
        Class<?> generatedClass = classLoader.defineClass("MyGeneratedClass", bytecode);
        Method mainMethod = generatedClass.getMethod("main", String[].class);
        mainMethod.invoke(null, (Object) new String[]{});
    }

    // 자바 클래스를 로드하는 사용자 정의 클래스 로더
    private static class CustomClassLoader extends ClassLoader {
        public Class<?> defineClass(String name, byte[] b) {
            return defineClass(name, b, 0, b.length);
        }
    }
}