package example.web

import example.SpringBootSpecification
import org.springframework.ui.Model

class HelloControllerSpec extends SpringBootSpecification {

    def "モックテスト"() {
        setup:
        def controller = new HelloController()

        and: '引数のModelのモックを作成してaddAttribute()がyamatoで一回呼ばれている事を確認'
        Model model = Mock(Model) {
            1 * addAttribute("name", "yamato")
        }

        expect:
        controller.home("yamato", model) == "hello"
    }

    def "インスタンス取得テスト"() {
        setup: 'contextからgetBeanでDI済みのインスタンスを取得'
        def bean = context.getBean("helloController", HelloController.class)
        Model model = Mock(Model) {
            1 * addAttribute("name", "yamato")
        }

        expect:
        bean.home("yamato", model) == "hello"
    }
}
