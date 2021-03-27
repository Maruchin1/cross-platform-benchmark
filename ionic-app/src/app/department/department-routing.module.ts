import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DepartmentPage} from './department.page';

const routes: Routes = [
  {
    path: '',
    component: DepartmentPage,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepartmentPageRoutingModule {
}
