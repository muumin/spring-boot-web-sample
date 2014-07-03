class ClassSample

  # インスタンス変数
  name: null

  # 他の関数の呼び出しは "@間数名()" で
  start_sample: () ->
    @hello()
    @name = "shin"
    @my_name()
    @map_sample()

  # Rubyみたいに、引数にデフォルト値を指定できます
  # Rubyみたいに、文字列に#{}で値を埋め込めます
  hello: (str = "world")->
    alert "hello #{str}"

  # インスタンス変数の参照は "@変数名" で
  my_name: () ->
    alert "myname is #{@name}"

  # 配列内の各要素に処理を施した新たな配列をかえします
  # 無名関数は "(引数) -> 処理" です
  map_sample: () ->
    array = [1, 2, 3, 4, 5, 6]
    new_array = array.map (e) ->
      e * e
    console.log new_array

