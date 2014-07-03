package example.web

import example.SpringBootSpecification
import example.domain.User
import example.service.UserService
import wslite.rest.RESTClient

class RestSampleControllerSpec extends SpringBootSpecification {
    def userList = [
            new User(name: "tarou", age: 18),
            new User(name: "hanako", age: 21)
    ]

    def "モックテスト"() {
        setup:
        def controller = new RestSampleController()

        and: 'UserService.getUser()でダミーのListを返す'
        controller.userService = Mock(UserService) {
            1 * getUser() >> userList
        }

        when:
        def ret = controller.home()

        then:
        def i = 0
        ret.each {
            assert it.name == userList[i].name
            assert it.age == userList[i].age
            i++
        }
    }

    def "インスタンス取得テスト"() {
        setup: 'contextからgetBeanでDI済みのインスタンスを取得'
        def bean = context.getBean("restSampleController", RestSampleController.class)

        when:
        def ret = bean.home()

        then:
        def i = 0
        ret.each {
            assert it.name == userList[i].name
            assert it.age == userList[i].age
            i++
        }
    }

    def "groovy-wsliteを使用したJsonの取得結果テスト"() {
        when:
        def response = new RESTClient("http://localhost:8080/rest").get()

        then:
        200 == response.statusCode

        and:
        def i = 0
        response.json.each {
            assert it.name == userList[i].name
            assert it.age == userList[i].age
            i++
        }

        and: '結果を文字列で比較'
        // 結果のJsonを整形して出力
        // println groovy.json.JsonOutput.prettyPrint(response.text)
        response.text == '[{"name":"tarou","age":18},{"name":"hanako","age":21}]'
    }
}
