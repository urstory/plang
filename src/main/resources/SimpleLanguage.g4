grammar SimpleLanguage;

program: statement+ EOF;

statement
    : declaration
    | assignment
    | ifStatement
    | block
    | printStatement
    ;

block : '{' statement* '}';

declaration: 'int' ID ';';

assignment: ID '=' expression ';';

printStatement: 'print' '(' ID ')' ';';

ifStatement: 'if' '(' condition ')' statement ('else' statement)?;

condition: expression ('<' | '>' | '==' | '!=' | '<=' | '>=') expression;

expression: expression ('+'|'-') term
    | term
    ;

term: term ('*'|'/') factor
    | factor
    ;

factor: ID | NUMBER | '(' expression ')';/

ID: [a-zA-Z]+;
NUMBER: [0-9]+;
WS: [ \t\r\n]+ -> skip;
