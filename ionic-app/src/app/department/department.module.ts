import {IonicModule} from '@ionic/angular';
import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {DepartmentPage} from './department.page';

import {DepartmentPageRoutingModule} from './department-routing.module';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    RouterModule.forChild([{path: '', component: DepartmentPage}]),
    DepartmentPageRoutingModule,
  ],
  declarations: [DepartmentPage]
})
export class DepartmentPageModule {
}
