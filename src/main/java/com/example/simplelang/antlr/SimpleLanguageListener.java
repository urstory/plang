// Generated from D:/DEVEL/urstory/plang/plang/src/main/resources\SimpleLanguage.g4 by ANTLR 4.12.0
package com.example.simplelang.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SimpleLanguageParser}.
 */
public interface SimpleLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SimpleLanguageParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SimpleLanguageParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SimpleLanguageParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SimpleLanguageParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SimpleLanguageParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SimpleLanguageParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(SimpleLanguageParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(SimpleLanguageParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(SimpleLanguageParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(SimpleLanguageParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(SimpleLanguageParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(SimpleLanguageParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(SimpleLanguageParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(SimpleLanguageParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(SimpleLanguageParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(SimpleLanguageParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SimpleLanguageParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SimpleLanguageParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SimpleLanguageParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SimpleLanguageParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SimpleLanguageParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(SimpleLanguageParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SimpleLanguageParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(SimpleLanguageParser.FactorContext ctx);
}