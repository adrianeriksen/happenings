<#macro inputField label type name id>
<div class="mb-6">
    <label for="${id}" class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2">${label}</label>
    <input type="${type}" id="${id}" name="${name}" class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500" />
</div>
</#macro>