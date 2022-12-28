# blog-spring

ブログ管理アプリケーション

## 利用する

### 動作要件

- Java 17
- PostgreSQL 14

### 手順

1. アプリケーションを実行する
2. ユーザーを登録する
3. ログインする

#### 1. アプリケーションを実行する

```shell
./gradlew bootjar
java -jar libs/blog-spring.jar
```

### 2. ユーザーを登録する

users テーブルと authorities テーブルにユーザー情報を登録する。

パスワードは Spring Boot CLI を使って予めハッシュ化する。[参考](https://spring.pleiades.io/spring-security/reference/features/authentication/password-storage.html#authentication-password-storage-boot-cli)

### 3. ログインする

`/login` にアクセスし、登録したユーザー情報でログインする。



