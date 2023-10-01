import { Component } from '@angular/core';
import {PersonService} from "../../services/person.service";
import {ToastrService} from "ngx-toastr";
interface PersonDto {
  id: string,
  name: string,
  birthday: string
}

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent {
  id: string = '';
  name: string = '';
  birthday: string = '';

  constructor(private service : PersonService, private toastrService : ToastrService ) {
  }

  onClick() {
    if (!/^([a-zA-Z]\d{4})$/.test(this.id)){
      this.toastrService.error("Wrong id format");
      return;
    }
    if(!/^\d{2}-\d{2}-\d{4}$/.test(this.birthday)){
      this.toastrService.error("Wrong date format");
      return;
    }
    const person: PersonDto = {
      id: this.id,
      name: this.name,
      birthday: this.birthday
    }

    this.service.addPerson(person).subscribe(
      (response : string) => {
        console.log(response)
        this.toastrService.success(response)
      },
      // error => {
      //   console.log(error)
      //   this.toastrService.error(error);
      // }
    );
    // Tutaj możesz dodać logikę obsługi formularza, np. wysłanie danych na serwer
    console.log('ID:', this.id);
    console.log('Name:', this.name);
    console.log('Birthday:', this.birthday);
  }
}
