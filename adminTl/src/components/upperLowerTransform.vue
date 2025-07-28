<template>
  <input type="text" v-model="newValue" v-bind:id="idValue"/>
</template>
<script>
export default {
  name: 'toupper',
  props: ['setValue', 'idValue', 'type'],
  data: function () {
    return {}
  },
  computed: {
    newValue: {
      get: function () {
        return this.value;
      },
      set: function (value) {
        if (this.type == 'toUpper') {
          $('#' + this.idValue).val(value.toUpperCase());
          this.$emit('input', value.toUpperCase());
        } else if (this.type == 'toLower') {
          $('#' + this.idValue).val(value.toLowerCase());
          this.$emit('input', value.toLowerCase());
        } else {
          $('#' + this.idValue).val(value);
          this.$emit('input', value);
        }
      }
    }
  },
  methods: {}
}
</script>
