import {Component, OnInit} from '@angular/core';
import {PersonService} from "../../services/person.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit{
  persons: any[];

  constructor(private service: PersonService, private toastrService : ToastrService) {
    this.persons = [];
  }

  ngOnInit(){
    this.fetchPersons();
  }

  fetchPersons() {
    this.service.getPersons().subscribe(
      (response: any[]) => {
        console.log(typeof(response[0].year));
        this.persons = response;
      },
      error => {
        this.toastrService.error(error);
      }
    );
  }

}
