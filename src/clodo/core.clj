(ns clodo.core
  (:gen-class)
  (:require [clodo.display :as display]))

(defn application-loop
  "Keeps the programm alive"
  [& [todos func]]
  (display/welcome-screen)
  (print "Input: ")
  (flush)
  (let [todo-list (cond
                    (nil? func) (if (nil? todos) [] todos)
                    (nil? todos) (func [])
                    :else (let [result (func todos)] (if (vector? result) result todos)))
        input (when (nil? func) (read-line))]
    (case input
      "add" (application-loop todo-list display/add-screen)
      "list" (application-loop todo-list display/list-screen)
      "del" (application-loop todo-list display/delete-screen)
      "comp" (application-loop todo-list display/complete-screen)
      "expo" (application-loop todo-list display/export-screen)
      "impo" (application-loop nil display/import-screen)
      "exit" (System/exit 0)
      (application-loop todo-list))))

(defn -main
  []
  (application-loop))