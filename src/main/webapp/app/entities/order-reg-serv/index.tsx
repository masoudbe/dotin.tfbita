import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OrderRegServ from './order-reg-serv';
import OrderRegServDetail from './order-reg-serv-detail';
import OrderRegServUpdate from './order-reg-serv-update';
import OrderRegServDeleteDialog from './order-reg-serv-delete-dialog';

const OrderRegServRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<OrderRegServ />} />
    <Route path="new" element={<OrderRegServUpdate />} />
    <Route path=":id">
      <Route index element={<OrderRegServDetail />} />
      <Route path="edit" element={<OrderRegServUpdate />} />
      <Route path="delete" element={<OrderRegServDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OrderRegServRoutes;
