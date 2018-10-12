<template>
  <b-container>
    <b-row class="justify-content-md-center">
      <b-col sm="6">
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
          <b-form-group id="inputGroup1"
                        label="Name:"
                        label-for="hotelName"
                        description="Hotel name">
            <b-form-input id="hotelName"
                          type="text"
                          v-model="hotel.name"
                          required
                          placeholder="Enter name">
            </b-form-input>
          </b-form-group>
          <b-form-group id="inputGroup2"
                        label="Cateogory:"
                        label-for="exampleInput2">
            <b-form-select id="category"
                           :options="options"
                           v-model="hotel.category"
                           required
                           placeholder="Enter category">
            </b-form-select>
          </b-form-group>
          <b-button type="submit" variant="primary">Submit</b-button>
          <b-button type="reset" variant="danger">Reset</b-button>
        </b-form>
      </b-col>
    </b-row>
    <br/>
    <b-row class="justify-content-md-center">
      <b-col sm="8">
        <b-table striped hover :items="hotels"></b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      hotel: {
        name: '',
        category: ''
      },
      hotels: [],
      options: [
        {value: '5', text: '★★★★★'},
        {value: '4', text: '★★★★'},
        {value: '3', text: '★★★'},
        {value: '2', text: '★★'},
        {value: '1', text: '★'},
      ],
      show: true
    }
  },
  methods: {
    onSubmit (evt) {
      evt.preventDefault();
      this.hotels.push(this.hotel);
      this.hotel = {
        name: '',
        category: ''
      }
    },
    onReset (evt) {
      evt.preventDefault();
      /* Reset our form values */
      this.form.email = '';
      this.form.name = '';
      this.form.food = null;
      this.form.checked = [];
      /* Trick to reset/clear native browser form validation state */
      this.show = false;
      this.$nextTick(() => { this.show = true });
    }
  }
}
</script>
<!--
<template xmlns:v-on="http://www.w3.org/1999/xhtml">
  <div class="title">
    <h1>Hotels</h1>

    <b-form v-on:submit.prevent="onSubmit" inline>
      <b-input class="mb-2 mr-sm-2 mb-sm-0" name="name" type="text" placeholder="Name" v-model="hotel.name" />
      <b-select v-model="hotel.category" :options="options" class="mb-2 mr-sm-2 mb-sm-0"/>
      <b-button v-if="updating">Update</b-button>
      <b-button variant="primary" v-else>Add</b-button>
    </b-form>

    <b-table striped hover :items="hotels"></b-table>
    <table>
      <tr>
        <th><label class="sr-only">Name</label>Name</th>
        <th>Category</th>
      </tr>
      <tr v-for="(b, index) in hotels">
        <td >{{ b.name }}</td>
        <td>{{ b.category }}</td>
        <td v-on:click.prevent="onEdit(index)"><a>✎</a></td>
        <td v-on:click.prevent="onDelete(index)"><a>✗</a></td>
      </tr>
    </table>
  </div>
</template>

<script>
  export default {
    name: 'admin-hotels',
    data () {
      return {
        updating: false,
        updateIndex: 0,
        hotels: [],
        hotel: {
          name: '',
          category: ''
        },

    },
    methods: {
      onSubmit() {
        if(this.updating) {
          this.onUpdate();
          return;
        }
        this.hotels.push(this.hotel);
        this.hotel = {
          name: '',
          category: ''
        }
      }
  }
  }
</script>
-->
