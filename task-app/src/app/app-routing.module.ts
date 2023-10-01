import { NgModule } from '@angular/core';

import { RouterModule, Routes} from "@angular/router";
import {PersonListComponent} from "./components/person-list/person-list.component";
import {PersonFormComponent} from "./components/person-form/person-form.component";
import {SummaryComponent} from "./components/summary/summary.component";

const routes: Routes = [
  /*
  { path: '', redirectTo: '/table1', pathMatch: 'full' },
  { path: 'table1', component: Table1Component }
  * */
  {path: '', redirectTo: '/persons', pathMatch: 'full'},
  {path: 'persons', component: PersonListComponent },
  {path: 'person/add', component: PersonFormComponent},
  {path: 'persons/summary', component: SummaryComponent}
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
