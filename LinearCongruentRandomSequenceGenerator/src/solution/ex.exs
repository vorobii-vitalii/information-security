defmodule Todo do
    def new do
      %{}
    end

    def add(todos, title, text) do
      Map.insert(todos, title, [text], fn texts -> [text | texts] end)
    end

end

todos = Todo.new() |> Todo.add("Hello", "World") |> Todo.add("Hello", "123");

IO.puts todos