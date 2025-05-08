package extra.pinyin;


import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PinyinTest {

    public static void main(String[] args) {
        String pinyin = PinyinHelper.toPinyin("解师傅", PinyinStyleEnum.FIRST_LETTER);
        System.out.println(pinyin);
    }

}
