[#ftl]

[#import '_imports/_common.ftlh' as c]

[@c.post title="Programming blog"]
<h1> </h1>
<a href="/post/{slug}" class="button"><strong>>Read more<<</strong></a>
<p>${post.author}</p>
<p>${post.title}</p>
<p>${post.perex?no_esc}</p>
<p>${post.body}</p>
<a href="/" class="button">Back to the main page</a>

[/@c.post]