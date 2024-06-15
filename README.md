# Task 7 - Blog

Create a simple application to display blog posts. The front page will display a summary of the last 20 posts, the most recent will be on
at the top. It will display the headline, perex, date published and author. There will be a link next to the post, e.g. "Read more", which will 
lead to a detail page with in addition to the above information, the full post. The URL of the post will be of the form `/post/{slug}`, 
where `slug` is the value from the corresponding column in the database. The detail page will link back to the front page.

The database contains a single table named `post`. See `src/resources/db/migration/V1__init.sql` for how to create the table.

* `id` - the numeric identifier of the entry, the primary key - in Java use the `Long` type for it; it won't be of any use to the application 
at the moment, but the database has every decent well-behaved record has its unique identifier
* `slug` - the part of the URL after `/post/` that identifies the post (the so-called "pretty URL")
* `author` - the name of the author
* `title` - the title of the post
* `perex` - the HTML code of the first paragraph of the post, which is displayed on the home page
* `body` - the continuation of the post after the perex, HTML code
* `published` - the date of publication, if it is `NULL` or in the future, the post is not yet displayed on the front page (check how the database behaves,
  when the record is `NULL` and the condition says the date must be less than some value)

The data table is empty after the first run of the application, to test it you will need to insert some posts into the table using the tools in IntelliJ Idea.
The connection URL, which is entered when configuring the Database panel in IntelliJ Idea, can be found in the `src/main/resources/application.yaml` file.

1. Use this repository as a template from which to create a repository in your GitHub account.
1. Clone the repository **from your GitHub account** to your local machine.
1. Run your cloned application to spawn the database. Meanwhile, the browser will display the page [http://localhost:8080/](http://localhost:8080/)
   only an error, there is no controller in the application.
1. Run the Database panel in IntelliJ Idea so you can check what's in the database (there's just an empty table, no entries 😉 ). The connection URL that is
   is entered when configuring the *Database* panel in IntelliJ Idea, can be found in the `src/main/resources/application.yaml` file. The easiest way is to use *DataSource from
   URL*.
1. When the application starts, the database is empty, the startup script does not fill in any data. Use the *Database* panel in IntelliJ Idea to connect to the database and add
   several posts to a future blog. You can use HTML code in the perex and body (`body`) - be sure to use at least the `<p>...</p>` paragraphs.
   Create the `Post` entity and create the fields and properties corresponding to the database table. Don't forget the `@Entity`, `@Id` and `@GeneratedValue` annotations.
1. Create a `JpaRepository` for the `Post` entity (name it `PostRepository`).
1. Create a `PostService` that will use `@Autowired` to retrieve `PostRepository`.
1. In the `PostService`, create a method `list()` that will return a list of all posts (no paging or sorting yet). Next, create a method there
   method `singlePost(String slug)`, which will find one post according to the specified `slug` and return it.
1. Create a controller and two methods in it, to display the home page with a list of posts and to display one complete post. The controller will use
   the  service
   `PostService`, which is obtained using `@Autowired`. You don't have to deal with the case where the user makes up a post URL that doesn't exist.
1. Create templates for both controller methods. Appearance doesn't matter :-) To insert the HTML code from the model into the template, you need to use the Freemarker entry instead
   `${value?no_esc}` to ensure that Freemarker does not convert the `<` and `>` characters, but inserts them unaltered into the resulting file. See the documentation for details
   [no_esc](https://freemarker.apache.org/docs/ref_builtins_string.html#ref_builtin_no_esc) of Freemarker.
1. Edit the `list()` method in `PostService` to use `Pageable` and limit the result to 20 records. To create a proper `Pageable`, use a static
   method `PageRequest.of(0, 20)`. Create a method in the repository that will return Page<Post>, use `Pageable` to limit the number of records, retrieve only
   posts,
   that have a publication date and are not in the future, and sorts the entries in descending order by publication date. The `sort` entry of `Pageable` will not be used for sorting (
   this is used when the user should be able to change the sorting method - but we want to always sort entries from newest to oldest). Instead
   the correct method name in the repository will be used (so the text `OrderBy` will be part of the method name).
1. *Bonus*: You can modify the template for listing the entries so that you can page through them. However, the page numbering will not be used, instead it will be at the bottom
   on the page, just the "previous" and "next" links. Use the `hasPrevious()` and `hasNext()` methods on the `Page` interface to do this.
1. Check that everything works.
1. *Commit* and *push* the changes (the resulting code) to your GitHub repository.
1. Paste the link to your repository into a task on https://moje.czechitas.cz.
1. *Super bonus*: You can also add an administration page to the app, which will allow you to add, edit, and delete commits.





# Úkol 7 – Blog

Vytvoříme jednoduchou aplikaci pro zobrazování blogových zápisků. Na titulní stránce se bude zobrazovat přehled dvaceti posledních zápisků, nejnovější bude na
prvním místě. Zobrazovat se bude titulek, perex, datum publikování a autor. U zápisku bude odkaz např. „Přečíst“, který povede na stránku s detailem, kde bude
vedle výše uvedených informací také celý zápisek. URL zápisku bude ve tvaru `/post/{slug}`, kde `slug` je hodnota z odpovídajícího sloupce v databázi. Na
stránce s detailem bude odkaz zpět na titulní stránku.

Databáze obsahuje jednu tabulku pojmenovanou `post`. Jak se tabulka vytváří se podívej v souboru `src/resources/db/migration/V1__init.sql`.

* `id` – číselný identifikátor zápisku, primární klíč – v Javě pro něj použij typ `Long`; aplikaci v tuto chvíli k ničemu nebude, ale v databázi má každý slušně
  vychovnaý záznam svůj jednoznačný identifikátor
* `slug` – část URL za `/post/`, která identifikuje zápisek (tzv. „hezká URL“)
* `author` – jméno autora
* `title` – titulek zápisku
* `perex` – perex, HTML kód prvního odstavce zápisku, který se zobrazuje na úvodní stránce
* `body` – pokračování zápisku za perexem, HTML kód
* `published` – datum publikování, pokud bude `NULL` nebo v budoucnosti, zápisek se ještě na titulní stránce nezobrazuje (vyzkoušej si, jak se chová databáze,
  když je v záznamu hodnota `NULL` a v podmínce je řečeno, že datum musí být menší než nějaká hodnota)

Tabulka s daty je po prvním spuštění aplikace prázdná, pro otestování bude potřeba si nějaké zápisky do tabulky vložit pomocí nástrojů v IntelliJ Idea.
Připojovací URL, které se zadává při konfiguraci panelu Database v IntelliJ Idea, najdeš v souboru `src/main/resources/application.yaml`.

1. Použij toto repository jako šablonu (Use this template), ze které si vytvoříš repository ve svém účtu na GitHubu.
1. Naklonuj si repository **ze svého účtu** na GitHubu na lokální počítač.
1. Spusť si naklonovanou aplikaci, aby se vytvotřila databáze. V prohlížeči se na stránce [http://localhost:8080/](http://localhost:8080/) zatím bude zobrazovat
   jen chyba, v aplikaci není žádný controller.
1. Zprovozni si panel Database v IntelliJ Idea, ať si můžeš ověřit, co je v databázi (je tam jen prázdná tabulka, žádné zápisky 😉). Připojovací URL, které se
   zadává při konfiguraci panelu *Database* v IntelliJ Idea, najdeš v souboru `src/main/resources/application.yaml`. Nejjednodušší je použít *DataSource from
   URL*.
1. Po spuštění aplikace je databáze prázdná, zakládací skript neplní žádná data. Použij v IntelliJ Idea panel *Database* pro připojení k databázi a přidej
   několik příspěvků do budoucího blogu. V perexu a tělu (`body`) můžeš použít HTML kód – určitě použij alespoň odstavce `<p>…</p>`.
1. Vytvoř entitu `Post` a v ní vytvoř fieldy a properties odpovídající databázové tabulce. Nezpomeň na anotace `@Entity`, `@Id` a `@GeneratedValue`.
1. Vytvoř `JpaRepository` pro entitu `Post` (pojmenuj ji `PostRepository`).
1. Vytvoř službu `PostService`, která bude pomoc `@Autowired` získávat `PostRepository`.
1. Ve službě `PostService` vytvoř metodu `list()`, která bude vracet seznam všech postů (zatím bez stránkování a řazení). Dále tam vytvoř metodu
   `singlePost(String slug)`, která najde jeden post podle zadaného `slug` a ten vrátí.
1. Vytvoř controller a v něm dvě metody, pro zobrazení úvodí stránky se seznamem postů a pro zobrazení jednoho kompletního postu. Controller bude používat
   službu
   `PostService`, kterou získá pomocí `@Autowired`. Nemusíš řešit případ, když si uživatel vymyslí URL postu, který neexistuje.
1. Vytvoř šablony pro obě metody controlleru. Na vzhledu nezáleží :-) Pro vložení HTML kódu z modelu do šablony je nutné místo použít ve Freemarkeru zápis
   `${value?no_esc}`, který zajistí, že Freemarker nebude převádět znaky `<` a `>`, ale vloží je bezezměny do výsledného souboru. Detaily najdeš v dokumentaci
   [no_esc](https://freemarker.apache.org/docs/ref_builtins_string.html#ref_builtin_no_esc) Freemarkeru.
1. Uprav metodu `list()` v `PostService` tak, aby používala `Pageable` a omezila výsledek na 20 záznamů. Pro vytvoření správného `Pageable` použij statickou
   metodu `PageRequest.of(0, 20)`. Vytvoř v repository metodu, která bude vracet Page<Post>, bude používat `Pageable` pro omezení počtu záznamů, načte pouze
   posty,
   které mají datum publikace a není v budoucnosti, a seřadí záznamy sestupně podle data publikace. Pro řazení se nebude používat položka `sort` z `Pageable` (
   to se používá v případě, kdy má uživoatel mít možnost měnit způsob řazení – my ale chceme zápisky seřadit vždy od nejnovějšího po nejstarší). Místo toho se
   použije správný název metody v repository (součástí názvu metody bude tedy text `OrderBy`).
1. *Bonus*: Můžeš upravit šablonu pro výpis seznamu zápisků tak, aby bylo možné stránkami listovat. Nepoužije se ale číslování stránek, místo toho budou dole
   na stránce jen odkazy „předchozí“ a „další“. Použij k tomu metody `hasPrevious()` a `hasNext()` na rozhraní `Page`.
1. Zkontroluj, zda vše funguje.
1. *Commitni* a *pushnni* změny (výsledný kód) do svého repository na GitHubu.
1. Vlož odkaz na své repository do úkolu na portálu https://moje.czechitas.cz.
1. *Super bonus*: Můžeš do aplikace přidat i administraci – stránku, přes kterou bude možné přidávat zápisky, upravovat je a mazat.

## Odkazy

* odkaz na stránku [Lekce 10](https://java.czechitas.cz/2024-jaro/java-2-online/lekce-10.html)
* Java SE 21 [Javadoc](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/) – dokumentace všech tříd, které jsou součástí základní Javy ve verzi 21.
* Dokumentace [Spring Boot](https://spring.io/projects/spring-boot#learn) – odsud je anotace `@SpringBootApplication` a třída `SpringApplication`.
* Dokumentace [Spring Framework](https://spring.io/projects/spring-framework#learn) – odsud jsou anotace `@Controller`, `@GetRequest` a třída `ModelAndView`.
* Dokumentace [Freemarker](https://freemarker.apache.org/docs/) – šablonovací systém pro HTML použitý v projektu.
* [Unsplash](https://unsplash.com) – obrázky a fotografie k použití zdarma
