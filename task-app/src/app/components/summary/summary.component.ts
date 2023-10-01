import {Component, OnInit} from '@angular/core';
import {PersonService} from "../../services/person.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css']
})
export class SummaryComponent implements OnInit{
  persons: any;

  constructor(private service: PersonService, private toastrService : ToastrService) {

  }
  ngOnInit(): void {
    this.getSummary();
  }

  getSummary() {
    this.service.getSummary().subscribe(
      (response) => {
        this.persons = response;
      },
      (error) => {
        this.toastrService.error(error);
      }
    );
  }

}
