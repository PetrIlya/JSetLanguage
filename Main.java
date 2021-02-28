package com.github.PetrIlya;

import com.github.PetrIlya.gen.JSetLexer;
import com.github.PetrIlya.gen.JSetParser;
import com.github.PetrIlya.visitor.ClassVisitor;
import com.sun.org.apache.bcel.internal.Constants;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.generic.*;

import java.io.*;
import java.util.Collections;

public class Main {

    public static void sample() throws IOException {
        File file = new File("Main.class");
        file.delete();
        file.createNewFile();
        OutputStream os = new FileOutputStream(file);
        JSetLexer lexer = new JSetLexer(CharStreams.fromStream(new FileInputStream("simple.txt")));
        CommonTokenStream stream = new CommonTokenStream(lexer);
        JSetParser parser = new JSetParser(stream);
        JSetParser.ClassDeclarationContext context = parser.classDeclaration();
        ClassVisitor visitor = new ClassVisitor();
        visitor.visitClassDeclaration(context);
        visitor.getClassGen().getJavaClass().dump(os);
    }

    public static void main(String[] args) throws IOException {
        sample();
    }
}
