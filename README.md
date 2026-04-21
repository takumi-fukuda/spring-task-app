# タスク管理アプリ

## 概要
Spring BootとMySQLを使用して作成したタスク管理アプリです
ユーザーごとにログインし、自分のタスクを管理できます

## 機能
- ログイン機能
- タスク一覧表示
- タスク追加
- タスク削除
- タスク完了
- タスクの編集
- ユーザーごとのタスク管理

## 使用技術
- Java
- Spring Boot
- MySQL
- HTML

## 使い方
1. ログイン
以下のユーザーでログインできます
・username: takasi / password: 1234
・username: hirosi / password: 5678

2. タスク操作
・タスクを入力して追加
・完了ボタンでタスク完了
・削除ボタンで削除
・編集ボタンで内容変更

## データベース構成
usersテーブル
・id
・username
・password

tasksテーブル
・id
・title
・completed
・user_id

## 工夫した点
・セッションを使ったログイン管理
・ユーザーごとのタスクを分離

## 今後の改善予定
- ログアウト機能の追加
