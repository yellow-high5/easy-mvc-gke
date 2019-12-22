<template>
  <v-container>
    <h1 style="text-align:left">MySQL DB Post Form</h1>
        <v-form
          ref="form"
          v-model="valid"
          class="user-form"
        >
        <v-container fluid>
          <v-row>
            <v-col
              cols="12"
              md="12"
            >
            <v-text-field
              name="name"
              v-model="form_name"
              :counter="20"
              :rules="nameRules"
              label="名前"
              append-icon="person"
              required
            ></v-text-field>
            </v-col>
            <v-col
              cols="12"
              md="12"
            >
            <v-text-field
              name="email"
              v-model="form_email"
              :counter="50"
              :rules="emailRules"
              label="メールアドレス"
              append-icon="email"
              required
            ></v-text-field>
            </v-col>
            <v-btn @click="post">ユーザー作成</v-btn>
          </v-row>
        </v-container>
        </v-form>
  </v-container>
</template>

<script>
console.log(`Your access API PATH is ${process.env.VUE_APP_API_ORIGIN}`);
export default {
  name: 'UserForm',
  data: function(){
    return {
      form_name: "",
      form_email: "",
    }
  },
  methods: {
    post: function(){
      fetch(`${process.env.VUE_APP_API_ORIGIN}/users/new/?name=${this.form_name}&email=${this.form_email}`, {
        method: "POST",
        headers:{
          'Content-Type': 'application/json'
        }
      })
      .then(response =>  {
        if(response){
          alert('Success!')
        }
        this.$router.push('/');
      })
      .catch((error) => {alert(error)});
    }
  }
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
.user-form {
  width: 360px;
}
</style>
