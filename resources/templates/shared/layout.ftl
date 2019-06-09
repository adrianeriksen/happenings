<#macro mainLayout>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />

    <title>Happenings</title>
    <link href="https://unpkg.com/tailwindcss@^1.0/dist/tailwind.min.css" rel="stylesheet" />
</head>
<body class="font-light">
<div class="container max-w-4xl my-8 mx-auto">
    <div class="flex -mx-2">
        <div class="w-1/4 px-3">
            <img src="/static/images/logo.png" alt="The Happenings Cat" />
        </div>
        <div class="w-3/4 px-3">
            <#nested />
        </div>
    </div>
</div>
</body>
</#macro>
