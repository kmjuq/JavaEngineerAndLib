package lox;

import lombok.Data;

@Data
public class Token {

    private TokenType tokenType;

    private String lexeme;

    private Object literal;

    private int line;

}
