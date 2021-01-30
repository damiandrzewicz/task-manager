<template>
    <div class="breadcrumb ma-3">
        <span v-for="crumb in items" :key="crumb.id" class="breadcrumb-item active" aria-current="page">
            <router-link :to="{ path: crumb.path }">{{ crumb.name }}</router-link>
            <span> / </span>
        </span>
    </div>
</template>

<script>
export default {
    name: "Breadcrumb",
    props: {
        items: {
            type: Array,
            default: () => ({})
        }
    },
}
</script>

<style>

</style>