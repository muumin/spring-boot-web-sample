Spring Boot Sample
=====================================

SpringBootをGradle + Spockでどこまで出来るかお試し。

あと毎年納税してあんまり使ってなかったIntelliJをガンガン使ってみたかった。

#本体

* SpringBoot
* Thymeleaf
* CoffeeScript
* Sass

※ CoffeeScriptについてはコンパイル環境構築だけで未使用

#開発
* Gradle

#テスト(Groovy)
* Groovy
* Spock
* groovy-wslite
* Geb

#テスト(JUnit)
* Mockito
* JSONIC
* Selenium

----

#起動に必要な物

[Node.js](http://nodejs.org/download/)

[Ruby](http://rubyinstaller.org/)

[FireFox](http://www.mozilla.org/ja/firefox/new/)

##Grunt

	npm install -g grunt-cli

##Sass

	gem install sass

##Node.jsパッケージ

	npm install

----

#起動

##Server起動

    grunt init
	gradlew bootRun

起動後以下にアクセスする

http://localhost:8080

http://localhost:8080/rest

##テスト起動

    gradlew test
    
ブラウザテストもするのでFireFoxが起動します。(ウィルスじゃないので焦らないように)

テスト結果は以下に保存されます。

* build/reports
* build/reports/geb (Gebの画面スナップショット)
* build/reports/selenium (Seleniumの画面スナップショット)

##開発

##build.gradleからインポート
[IntelliJ IDEA](http://www.jetbrains.com/idea/)なら「File」 > 「Open」でbuild.gradleを指定して取り込めばOK(なはず)です。

デフォルトではJava6構文になっているのでFile > Project Structure... > Project > Project language level で6.0から7.0に変更してください。

##ideaプラグイン
コマンドでIntelliJの設定ファイルを作成

    gradlew idea

作成後IntelliJで開けば良いです。

こちらの方法だと開いた後にVCSの設定が必要になります

##その他
IntelliJにGruntのviewがあるので「default」をダブルクリックするとwatch taskが起動してcoffeeとsassの監視をします。

画面テンプレートがThymeleafなのでLiveEditでsrc/resources/templatesのHTMLをデバック起動すると幸せになれます。

http://blog.jetbrains.com/jp/2013/09/12/170

(PhpStormの例ですがIntelliJでも同じです)

#TODO

* デバックはApplication.javaをデバック起動すればいけるっぽい
    * HotDeploy出来ないけどSpringLoaderあたり使えばいけるのかな？(未確認)

#参考サイト

https://github.com/spring-projects/spring-boot

http://www.slideshare.net/makingx/spring-4spring-boot-spring-jjug-jsug

http://acro-engineer.hatenablog.com/entry/2014/06/03/120128

https://github.com/geb/geb-example-gradle

https://github.com/yamkazu/spock-workshop

http://d.hatena.ne.jp/hiroe_orz17/20130418/1366246108

#感謝

@mike_neckさんにgradle ideaの設定を教えて貰ったので感謝！
