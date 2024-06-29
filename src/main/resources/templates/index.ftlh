[#ftl]
[#import '_imports/_common.ftlh' as c]

[@c.post title="Programming blog"]
    <h1>Here you can find all you´ve ever wish to know about programming</h1>
<table>
    <thead>
    <tr>
        <th>author</th>
        <th>title</th>
        <th>perex</th>
        <th>body</th>
        <th>published</th>
    </tr>
    </thead>
    <tbody>
    [#list posts.content as post]
    <tr>
    <td>${post.author}</td>
    <td>${post.title}</td>
    <td>${post.perex?no_esc}</td>
    <td>${post.published}</td>
</tr>
[/#list]
    </tbody>
</table>

[/@c.post]