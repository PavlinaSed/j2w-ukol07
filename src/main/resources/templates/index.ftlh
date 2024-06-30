[#ftl]


[#import '_imports/_common.ftlh' as c]

[@c.post title="Programming blog"]
    <h1>Here you can find all you´ve ever wish to know about programming</h1>
    <table>
        <thead>
        <tr>
            <th scope="col">author</th>
            <th scope="col">title</th>
            <th scope="col">perex</th>
            <th scope="col">body</th>
            <th scope="col">published</th>
        </tr>
        </thead>
        <tbody>
        [#list posts.content as post]
            <tr>
                <td>${post.author}</td>
                <td>${post.title}</td>
                <td>${post.perex?no_esc}</td>
                <td>${post.body}</td>
                <td>${post.published?date}</td>
                <td>
                    <a href="/${post.slug}">Read more</a>
                </td>
            </tr>
        [/#list]
        </tbody>
    </table>

[/@c.post]