<#import "shared/layout.ftl" as layout />

<@layout.mainLayout>
<h1 class="text-2xl">Happenings near you</h1>
<ul>
<#list events as event>
    <li>${event.title}</li>
</#list>
</ul>
</@layout.mainLayout>
