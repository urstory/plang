package com.example;

import com.example.simplelang.antlr.SimpleLanguageBaseListener;
import com.example.simplelang.antlr.SimpleLanguageParser;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.HashMap;
import java.util.Map;

public class BytecodeGenerator extends SimpleLanguageBaseListener implements Opcodes {
    private ClassWriter classWriter;
    private MethodVisitor methodVisitor;
    private Map<String, Integer> variableIndexes = new HashMap<>();
    private int localVarIndex = 1;

    public BytecodeGenerator() {
        classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
    }

    public byte[] generate(SimpleLanguageParser.ProgramContext programContext) {
        classWriter.visit(V1_8, ACC_PUBLIC, "MyGeneratedClass", null, "java/lang/Object", null);

        // 생성자 메서드 추가
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();

        // 프로그램 메서드 추가
        methodVisitor = classWriter.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        new ParseTreeWalker().walk(this, programContext);
        methodVisitor.visitInsn(RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();

        classWriter.visitEnd();

        // 바이트코드 생성을 완료하고 반환합니다.
        return classWriter.toByteArray();
    }

    @Override
    public void enterDeclaration(SimpleLanguageParser.DeclarationContext ctx) {
        String varName = ctx.ID().getText();
        variableIndexes.put(varName, localVarIndex++);
    }

    @Override
    public void enterAssignment(SimpleLanguageParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        Integer index = variableIndexes.get(varName);
        if (index == null) {
            throw new RuntimeException("Unknown variable: " + varName);
        }

        generateExpressionCode(ctx.expression());
        methodVisitor.visitVarInsn(ISTORE, index);

        // 값 출력
        printVar(varName);
    }

    private void printVar(String varName) {
        methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        methodVisitor.visitVarInsn(ILOAD, variableIndexes.get(varName));
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    }

    @Override
    public void enterIfStatement(SimpleLanguageParser.IfStatementContext ctx) {
        Label elseLabel = new Label();
        Label endLabel = new Label();

        // 조건 코드 생성
        generateConditionCode(ctx.condition(), elseLabel);

        // if 절 코드 생성
        new ParseTreeWalker().walk(this, ctx.statement(0));
        methodVisitor.visitJumpInsn(GOTO, endLabel);

        // else 절 코드 생성 (있으면)
        methodVisitor.visitLabel(elseLabel);
        if (ctx.statement().size() > 1) {
            new ParseTreeWalker().walk(this, ctx.statement(1));
        }
        methodVisitor.visitLabel(endLabel);
    }


    private void generateConditionCode(SimpleLanguageParser.ConditionContext ctx, Label elseLabel) {
        String operator = ctx.getChild(1).getText();
        int opcode = 0;

        switch (operator) {
            case "<":
                opcode = IF_ICMPLT;
                break;
            case ">":
                opcode = IF_ICMPGT;
                break;
            case "==":
                opcode = IF_ICMPEQ;
                break;
            case "!=":
                opcode = IF_ICMPNE;
                break;
            case "<=":
                opcode = IF_ICMPLE;
                break;
            case ">=":
                opcode = IF_ICMPGE;
                break;
            default:
                throw new RuntimeException("Invalid operator: " + operator);
        }

        generateExpressionCode(ctx.expression(0));
        generateExpressionCode(ctx.expression(1));
        methodVisitor.visitJumpInsn(opcode, elseLabel);
    }

    private void generateExpressionCode(SimpleLanguageParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 1) {
            generateTermCode(ctx.term());
        } else {
            generateExpressionCode(ctx.expression());
            generateTermCode(ctx.term());
            int opcode = ctx.getChild(1).getText().equals("+") ? IADD : ISUB;
            methodVisitor.visitInsn(opcode);
        }
    }

    private void generateTermCode(SimpleLanguageParser.TermContext ctx) {
        if (ctx.getChildCount() == 1) {
            generateFactorCode(ctx.factor());
        } else {
            generateTermCode(ctx.term());
            generateFactorCode(ctx.factor());
            int opcode = ctx.getChild(1).getText().equals("*") ? IMUL : IDIV;
            methodVisitor.visitInsn(opcode);
        }
    }

    private void generateFactorCode(SimpleLanguageParser.FactorContext ctx) {
        if (ctx.ID() != null) {
            String varName = ctx.ID().getText();
            Integer index = variableIndexes.get(varName);
            if (index == null) {
                throw new RuntimeException("Unknown variable: " + varName);
            }
            methodVisitor.visitVarInsn(ILOAD, index);
        } else if (ctx.NUMBER() != null) {
            methodVisitor.visitIntInsn(SIPUSH, Integer.parseInt(ctx.NUMBER().getText()));
        } else if (ctx.expression() != null) {
            generateExpressionCode(ctx.expression());
        }
    }

}
