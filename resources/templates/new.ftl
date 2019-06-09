<#import "shared/layout.ftl" as layout />
<#import "shared/form.ftl" as form />

<@layout.mainLayout>
<h1 class="text-2xl mb-6">What's happening?</h1>
<form action="/events/new" method="post">
    <@form.inputField label="Title" type="text" name="title" id="form-new-event-title" />
    <@form.inputField label="Starts At" type="datetime-local" name="starts-at" id="form-new-event-starts-at" />
    <@form.inputField label="Where" type="text" name="where" id="form-new-event-where" />
    <div>
        <button type="submit" class="shadow bg-blue-500 hover:bg-blue-400 focus:shadow-outline focus:outline-none text-xs text-white uppercase py-2 px-4 rounded">
            Create Event
        </button>
    </div>
</form>
</@layout.mainLayout>