<#import "shared/layout.ftl" as layout />

<@layout.mainLayout>
<h1 class="text-2xl">Happenings near you</h1>
<ul>
<#list events as event>
    <li><a href="/events/${event.id}">${event.title}</a></li>
</#list>
</ul>
</@layout.mainLayout>
