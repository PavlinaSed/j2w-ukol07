[#ftl]


[#import '_imports/_common.ftlh' as c]

[@c.page title="Programming blog"]
    <h1>Here you can find all you´ve ever wish to know about programming</h1>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">title</th>
            <th scope="col">perex</th>
            <th scope="col">author</th>
            <th scope="col">published</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        [#list posts.content as post]
            <tr>
                <td>${post.title}</td>
                <td>${post.perex?no_esc}</td>
                <td>${post.author}</td>
                <td>${post.published?date?string}</td>
                <td>
                    <a href="/post/${post.slug}">Read more</a>
                </td>
            </tr>
        [/#list]
        </tbody>
    </table>
[@c.pager /]

[/@c.page]