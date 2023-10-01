import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) { }

  getPersons() : Observable<any[]>{
    return this.http.get<any[]>(`${this.apiUrl}/persons`);
  }
  getSummary() : Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/persons/summary`);
  }

  addPerson(person : any) : Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/person`, person, { responseType: "text" as "json"});
  }
}
