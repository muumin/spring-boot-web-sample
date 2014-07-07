package example.web

import example.SpringBootGebSpecification
import example.web.Page.TopPage
import spock.lang.Unroll

class HelloControllerGebSpec extends SpringBootGebSpecification {

    @Unroll
    def 'トップ画面で「#inputWord」を入力してh1タグ内に「#expectWord」が表示されること'() {
        when:
        to TopPage

        then:
        at TopPage

        when:
        nameForm.field.value(inputWord)

        then:
        nameForm.field.value() == inputWord
        report("名前入力") // 途中エビデンス保存

        when:
        nameForm.button.click()

        then:
        hello.text() == expectWord

        and:
        nameForm.field.value() == ""

        where:
        inputWord | expectWord
        'groovy'  | 'Hello, groovy!'
        'google'  | 'Hello, google!'
        '山田'      | 'Hello, 山田!'
    }
}
