import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ServiceTariff from './service-tariff';
import ServiceTariffDetail from './service-tariff-detail';
import ServiceTariffUpdate from './service-tariff-update';
import ServiceTariffDeleteDialog from './service-tariff-delete-dialog';

const ServiceTariffRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ServiceTariff />} />
    <Route path="new" element={<ServiceTariffUpdate />} />
    <Route path=":id">
      <Route index element={<ServiceTariffDetail />} />
      <Route path="edit" element={<ServiceTariffUpdate />} />
      <Route path="delete" element={<ServiceTariffDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ServiceTariffRoutes;
