# 概要

前回でブログ記事に関する機能を一般的な開発を学習しましたね！

今回はブログに対するコメントを追加していこうと思います。

## 環境構築

## テーブル作成

データベース`blog-system` に `Comment` テーブルを`resources/db/create_comments_table.sql`に下記のカラムを用意し作成してください。

- ID
- ブログ ID
- コメント内容
- 作成日時
- 更新日時
- 削除日時

### 挑戦

ブログ ID は別テーブルの ID を参照する形になるので、外部キーを設定してみましょう。

# コメント一覧取得機能

以下の手順でブログ記事に紐づくコメントの一覧取得機能を作成していきましょう。

## Entity

`Comment.java`を作成し、Entity を定義してください。

## Repository の interface

`CommentRepository.java`を作成し、`findAllByBlogId`という名称で、Blog の ID を引数に取り、その Blog に紐づく全ての Comment オブジェクトを返却してください。

## Repository の実装

`CommentRepositoryImpl.java`を作成してください。
`findAllByBlogId`メソッドの中身を実装してください。以下は要件になります。

- ブログ ID に紐づくコメントを全て取得してください。
- エラーハンドリングの実装もしてください。

## Service

`CommentService.java`を作成し`listByBlogId`メソッドを作成してください。

## Controller

`CommentController.java`を作成し`listByBlogId`メソッドを作成してください。
エラーハンドリングも実装してください。

`comment/list`の view を返却してください。

## 動作確認

期待通りに登録されたデータが全取得できているか確認しましょう。

以下のクエリを実行してください。

```
INSERT INTO comments (blog_id, content, deleted_at)
VALUES
({blogID}, 'First Comment for First Blog', NULL),
({blogID}, 'Second Comment for First Blog', NULL)
```

※blogID に関しては自分の環境のデータに合わせて変更してください。

サーバーを起動して、`localhost:8080/blogs/{blogID}/comments`にアクセスして、2 件のコメントが取得できることを確認できたら正常に実装ができています！

# コメント作成機能

## Form

`CommentForm.java`を作成してください。

## Repository

`save`という名称でメソッドを作成してください。

## Repository 実装

入力された内容を保存してください。
エラーハンドリングの実装もしてください。

## Service

Form から Entity に詰め直して保存するように実装してください。

## Controller（Get リクエスト）

コメント作成画面のリクエストが来た際にフォームをレスポンスする実装をしてください。

`/blogs/{blogId}/comments/new`にリクエストが来た際に動作するようにしてください。

`comment/form`の view を返却してください。

## Controller（Post リクエスト）

コメント作成のリクエストが来た際に保存する実装をしてください。

`/blogs/{blogId}/comments`にリクエストが来た際に動作するようにしてください。

`comment/list`の view を返却してください。

## 動作確認

`localhost:8080/blogs/1/comments/new`にアクセスして、コメント内容を入力して保存ボタンを押下してください。一覧画面に遷移して、登録したコメントが表示されていたら正常に実装ができております。

# コメント更新機能

以下の手順でコメント更新機能を作成していきましょう。

## Repository

`update`という名称で Comment Entity を受け取り、空を返却するメソッドを作成してください。

## Repository 実装

先ほど interface で定義した内容を実装してください。

コメント内容を更新するクエリを作成し、更新日時を現在時刻に設定してください。

## Service

サービスロジックを作成してください。

Form から Entity に詰め直して更新するように実装してください。

## Controller（Get リクエスト）

コメント更新画面のリクエストが来た際に、既存のデータをフォームに表示する実装をしてください。

`/blogs/{blogId}/comments/{id}/edit`にリクエストが来た際に動作するようにしてください。

`comment/form`を view を返却してください。

## Controller（Post リクエスト）

コメント更新のリクエストが来た際に保存する実装をしてください。

`/blogs/{blogId}/comments/{id}`にリクエストが来た際に動作するようにしてください。

`comment/list`を view を返却してください。

## 動作確認

`localhost:8080/blogs/1/comments`にアクセスして、既存のデータのコメント内容を押下して、詳細画面に遷移してください。編集ボタンを押下して、更新画面に遷移してください。

コメント内容を更新して保存ボタンを押下してください。一覧画面に遷移して、更新したコメントが表示されていたら正常に実装ができております。

# コメント削除機能

以下の手順でコメント削除機能を作成していきましょう。

## Repository

データベースへの削除機能の interface を作成してください。

`delete`という名称で Comment の ID を受け取り、空を返却するメソッドを作成してください。

## Repository 実装

先ほど interface で定義した内容を実装してください。

削除日時を現在時刻に設定するクエリを作成してください。

## Service

サービスロジックを作成してください。

削除リクエストを受け取り、Repository の delete メソッドを呼び出す実装をしてください。

## Controller

削除リクエストが来た際に、削除処理を行う実装をしてください。

`/blogs/{blogId}/comments/{id}/delete`にリクエストが来た際に動作するようにしてください。

削除後は`comment/list`を view を返却してください。

## 動作確認

`localhost:8080/blogs/1/comments`にアクセスして、詳細画面に遷移してください。削除ボタンを押下してください。

一覧画面に遷移して、該当のコメントが削除されていたら正常に実装ができております。

# 提出

課題お疲れ様でした！  
CRUD 処理の基本は理解できたでしょうか？  
新規レポジトリを作成し、public 状態にして URL を教育担当者に共有をお願いします！
