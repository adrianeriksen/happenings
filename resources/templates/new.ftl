<#import "shared/layout.ftl" as layout />

<@layout.mainLayout>
<h1 class="text-2xl mb-6">What's happening?</h1>
<form action="/events/new" method="post">
    <div class="mb-6">
        <label for="form-new-event-title" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">Title</label>
        <input type="text" id="form-new-event-title" name="title" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" />
    </div>
    <div class="mb-6">
        <label for="form-new-event-starts-at" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">Starts At</label>
        <input type="datetime-local" id="form-new-event-starts-at" name="starts-at" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" />
    </div>
    <div>
        <button type="submit" class="shadow bg-blue-500 hover:bg-blue-400 focus:shadow-outline focus:outline-none text-xs text-white uppercase py-2 px-4 rounded">
            Create Event
        </button>
    </div>
</form>
</@layout.mainLayout>