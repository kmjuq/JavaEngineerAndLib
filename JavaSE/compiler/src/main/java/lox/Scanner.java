package lox;

import lombok.Data;

import java.util.List;

@Data
public class Scanner {

    /**
     * 源码
     */
    private String source;


    /**
     * 词元集合
     */
    private List<Token> tokens;

}
