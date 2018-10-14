<template>
  <b-container>
    <b-row class="justify-content-md-center">
      <b-col sm="6">
        <b-form @submit="onSubmit" v-if="show">
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
          <b-button type="submit" variant="primary">Create</b-button>
        </b-form>
      </b-col>
    </b-row>
    <br/>
    <b-row class="justify-content-md-center">
      <b-col sm="9">
        <div>
          <b-table striped hover small
                   :current-page="currentPage"
                   :totalRows="hotels.length"
                   :per-page="perPage"
                   :items="hotels"></b-table>
        </div>
      </b-col>
    </b-row>
    <b-row class="justify-content-md-center">
      <b-col sm="9">
        <b-pagination align="right"
                      size="sm"
                      :per-page="perPage"
                      v-model="currentPage"/>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  data () {
    return {
      hotel: {
        id: '',
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
      show: true,
      currentPage: 1,
      perPage: 20,
    }
  },
  methods: {
    onSubmit (evt) {
      evt.preventDefault();
      this.createHotel();
      this.hotel = {
        id: '',
        name: '',
        category: ''
      }
    },
    getHotels () {
        axios
        .get('http://localhost:18080/api/hotelapp/v1/hotels')
        .then(response => (this.hotels = response.data.content))
    },
    createHotel () {
      axios.post('http://localhost:18080/api/hotelapp/v1/hotels', this.hotel).then(result => {
        this.hotels.push(this.hotel);
        console.log(result.data)
      });
      this.getHotels();
    }
  },
  mounted () {
    this.getHotels()
  }
}
</script>
