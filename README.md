# Java Courses Homework

### Environment setup on macOS:

#### XAMPP

- run docker container with XAMPP:
`docker run -p 41062:80 -d -v ~/Documents/JavaForQACourse/www/:/www tomsik68/xampp`

#### AddressBook

- unzip `addressbook.zip` into `~/Documents/JavaForQACourse/www/`
- hit `http://localhost:41062`, go to phpMyAdmin
- create new database named `addressbook`
- copy/paste `addressbook.sql` from addressbook to SQL for new database in phpMyAdmin
- change AddressBook DB connection properties, if needed: `config/cfg.db.php`
- login into `http://localhost:41062/www/addressbook` with `admin`/`secret`
- validate installation with diagnostic page: `http://localhost:41062/www/addressbook/diag.php`
- in case of any problems read AddressBook documentation (_howto/_USER_GUIDE.docx)

#### Chrome Selenium driver:
- `brew install chromedriver`
