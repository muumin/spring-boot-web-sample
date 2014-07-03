package example.web.Page

import geb.Module

class NameModule extends Module {
    static content = {
        field { $('#name-text') }
        button(to: TopPage) {
            $('#name-btn')
        }
    }
}
