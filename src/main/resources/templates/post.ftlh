[#ftl]

[#import '_imports/_common.ftlh' as c]

[@c.page title="Programming blog"]
<h1> </h1>
<p>${post.author}</p>
<p>${post.title}</p>
<p>${post.perex?no_esc}</p>
<p>${post.body}</p>
<a href="/" class="button">Back to the main page</a>

[/@c.page]