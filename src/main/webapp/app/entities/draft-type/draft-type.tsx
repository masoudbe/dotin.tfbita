import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft-type.reducer';

export const DraftType = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftTypeList = useAppSelector(state => state.draftType.entities);
  const loading = useAppSelector(state => state.draftType.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="draft-type-heading" data-cy="DraftTypeHeading">
        <Translate contentKey="tfbitaApp.draftType.home.title">Draft Types</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draftType.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/draft-type/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draftType.home.createLabel">Create new Draft Type</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftTypeList && draftTypeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draftType.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('alarmTime')}>
                  <Translate contentKey="tfbitaApp.draftType.alarmTime">Alarm Time</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('alarmTime')} />
                </th>
                <th className="hand" onClick={sort('code')}>
                  <Translate contentKey="tfbitaApp.draftType.code">Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('code')} />
                </th>
                <th className="hand" onClick={sort('disableDate')}>
                  <Translate contentKey="tfbitaApp.draftType.disableDate">Disable Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('disableDate')} />
                </th>
                <th className="hand" onClick={sort('duration')}>
                  <Translate contentKey="tfbitaApp.draftType.duration">Duration</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('duration')} />
                </th>
                <th className="hand" onClick={sort('hasAssurance')}>
                  <Translate contentKey="tfbitaApp.draftType.hasAssurance">Has Assurance</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('hasAssurance')} />
                </th>
                <th className="hand" onClick={sort('hasSanction')}>
                  <Translate contentKey="tfbitaApp.draftType.hasSanction">Has Sanction</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('hasSanction')} />
                </th>
                <th className="hand" onClick={sort('latestCreditSerial')}>
                  <Translate contentKey="tfbitaApp.draftType.latestCreditSerial">Latest Credit Serial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('latestCreditSerial')} />
                </th>
                <th className="hand" onClick={sort('name')}>
                  <Translate contentKey="tfbitaApp.draftType.name">Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('name')} />
                </th>
                <th className="hand" onClick={sort('portal')}>
                  <Translate contentKey="tfbitaApp.draftType.portal">Portal</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('portal')} />
                </th>
                <th className="hand" onClick={sort('usable')}>
                  <Translate contentKey="tfbitaApp.draftType.usable">Usable</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('usable')} />
                </th>
                <th className="hand" onClick={sort('currenciesCodes')}>
                  <Translate contentKey="tfbitaApp.draftType.currenciesCodes">Currencies Codes</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currenciesCodes')} />
                </th>
                <th className="hand" onClick={sort('defaultCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.draftType.defaultCurrencyCode">Default Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('defaultCurrencyCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.type">Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.secondaryType">Secondary Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.division">Division</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.topicInfo">Topic Info</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.conditionInfo">Condition Info</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.accountInfo">Account Info</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.requestType">Request Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.acceptableProductTypes">Acceptable Product Types</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draftType.userGroups">User Groups</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftTypeList.map((draftType, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft-type/${draftType.id}`} color="link" size="sm">
                      {draftType.id}
                    </Button>
                  </td>
                  <td>{draftType.alarmTime}</td>
                  <td>{draftType.code}</td>
                  <td>{draftType.disableDate}</td>
                  <td>{draftType.duration}</td>
                  <td>{draftType.hasAssurance ? 'true' : 'false'}</td>
                  <td>{draftType.hasSanction ? 'true' : 'false'}</td>
                  <td>{draftType.latestCreditSerial}</td>
                  <td>{draftType.name}</td>
                  <td>{draftType.portal ? 'true' : 'false'}</td>
                  <td>{draftType.usable ? 'true' : 'false'}</td>
                  <td>{draftType.currenciesCodes}</td>
                  <td>{draftType.defaultCurrencyCode}</td>
                  <td>{draftType.type ? <Link to={`/category-element/${draftType.type.id}`}>{draftType.type.id}</Link> : ''}</td>
                  <td>
                    {draftType.secondaryType ? (
                      <Link to={`/category-element/${draftType.secondaryType.id}`}>{draftType.secondaryType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftType.division ? <Link to={`/category-element/${draftType.division.id}`}>{draftType.division.id}</Link> : ''}
                  </td>
                  <td>
                    {draftType.topicInfo ? (
                      <Link to={`/draft-type-topic-info/${draftType.topicInfo.id}`}>{draftType.topicInfo.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftType.conditionInfo ? (
                      <Link to={`/credit-type-condition-info/${draftType.conditionInfo.id}`}>{draftType.conditionInfo.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftType.accountInfo ? (
                      <Link to={`/draft-type-account-info/${draftType.accountInfo.id}`}>{draftType.accountInfo.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftType.requestType ? (
                      <Link to={`/draft-request-type/${draftType.requestType.id}`}>{draftType.requestType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftType.acceptableProductTypes ? (
                      <Link to={`/objective-category-element/${draftType.acceptableProductTypes.id}`}>
                        {draftType.acceptableProductTypes.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {draftType.userGroups
                      ? draftType.userGroups.map((val, j) => (
                          <span key={j}>
                            <Link to={`/string-value/${val.id}`}>{val.id}</Link>
                            {j === draftType.userGroups.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/draft-type/${draftType.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/draft-type/${draftType.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft-type/${draftType.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.draftType.home.notFound">No Draft Types found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DraftType;
